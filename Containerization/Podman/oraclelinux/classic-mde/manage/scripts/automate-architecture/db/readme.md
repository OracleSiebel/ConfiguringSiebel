# Oracle DB container and Pluggable DB

```
bash dropDB19 localhost/store/oracle/siebel/deploy-db/19c/sample:25.7
```
Due to some reasons the script in the container only extracts files 

exec inside DB container
```
podman exec -it oracle19c /bin/bash
cd /opt/oracle/oradata/ORCLCDB/
cp -r SAMPLE DEV
```
Copy  pdb_DEV.xml to /opt/oracle/oradata/ORCLCDB/DEV/
If you wanmt to create another DB instance like PRD follow below steps modify pdb_DEV.xml and replace DEV with PRD
```
 sqlplus /nolog
 connect sys/Oradoc_db1 as sysdba
 create pluggable database "DEV" using '/opt/oracle/oradata/ORCLCDB/DEV/pdb_DEV.xml' nocopy tempfile reuse;
 alter pluggable database "DEV" open read write;
 alter pluggable database "DEV" save state;
 commit;
 exit;
  ```
  Another set of commands to check parameters as per Oracle document PTUNGD_SIEBEL_ORA19C_V1-2.pdf 
  Performance Tuning Guidelines for Siebel CRM Applications Upgrading to Oracle 19c
  
 ```
  sqlplus /nolog
 connect sys/Oradoc_db1 as sysdba
CREATE USER DEV1 IDENTIFIED BY Mango_Matrix77 PROFILE DEFAULT; -- to create a developer user you can choose any username
GRANT SSE_ROLE TO DEV1;
GRANT CONNECT TO DEV1;

ALTER SESSION SET CONTAINER=DEV;
SELECT value FROM v$parameter WHERE name = 'optimizer_features_enable'; -- 19.1.0
SELECT value FROM v$parameter WHERE name = 'optimizer_adaptive_plans'; --TRUE
ALTER SYSTEM SET optimizer_adaptive_plans = TRUE SCOPE = BOTH;
SELECT value FROM v$parameter WHERE name = 'optimizer_adaptive_reporting_only'; --FALSE
ALTER SYSTEM SET optimizer_adaptive_reporting_only = TRUE SCOPE = BOTH;
SELECT a.ksppinm AS name, b.ksppstvl AS value FROM x$ksppi a, x$ksppcv b WHERE a.indx = b.indx AND a.ksppinm = '_optimizer_adaptive_cursor_sharing'; --TRUE
ALTER SYSTEM SET "_optimizer_adaptive_cursor_sharing" = FALSE SCOPE = BOTH;
SELECT a.ksppinm AS name, b.ksppstvl AS value FROM x$ksppi a, x$ksppcv b WHERE a.indx = b.indx AND a.ksppinm = '_optimizer_extended_cursor_sharing';
ALTER SYSTEM SET "_optimizer_extended_cursor_sharing" = NONE SCOPE = BOTH;
SELECT a.ksppinm AS name, b.ksppstvl AS value FROM x$ksppi a, x$ksppcv b WHERE a.indx = b.indx AND a.ksppinm = '_optimizer_extended_cursor_sharing_rel';--SIMPLE
ALTER SYSTEM SET "_optimizer_extended_cursor_sharing_rel" = NONE SCOPE = BOTH;
SELECT value FROM v$parameter WHERE name = 'optimizer_index_cost_adj';--100
ALTER SYSTEM SET "optimizer_index_cost_adj" = 1 SCOPE = BOTH;
SELECT value FROM v$parameter WHERE name = 'optimizer_dynamic_sampling';
ALTER SYSTEM SET "optimizer_dynamic_sampling" = 2 SCOPE = BOTH;
SELECT value FROM v$parameter WHERE name = 'db_file_multiblock_read_count';--20 (D)
SELECT value FROM v$parameter WHERE name = 'open_cursors';--300
ALTER SYSTEM SET "open_cursors" = 3000 SCOPE = BOTH;
SELECT value FROM v$parameter WHERE name = 'statistics_level';--TYPICAL
SELECT value FROM v$parameter WHERE name = 'session_cached_cursors';--50
ALTER SYSTEM SET session_cached_cursors = 100 SCOPE = SPFILE;
SELECT a.ksppinm AS name, b.ksppstvl AS value FROM x$ksppi a, x$ksppcv b WHERE a.indx = b.indx AND a.ksppinm = '_gc_defer_time';--0 ignore
SELECT a.ksppinm AS name, b.ksppstvl AS value FROM x$ksppi a, x$ksppcv b WHERE a.indx = b.indx AND a.ksppinm = '_like_with_bind_as_equality';--FALSE
ALTER SYSTEM SET "_like_with_bind_as_equality" = TRUE SCOPE = BOTH;
SELECT value FROM v$parameter WHERE name = 'sga_target';
SELECT value FROM v$parameter WHERE name = 'shared_pool_size';
SELECT value FROM v$parameter WHERE name = 'pga_aggregate_target';
SELECT * FROM v$sga_target_advice ORDER BY sga_size;
ALTER SYSTEM SET sga_target = 2304M SCOPE = SPFILE;
ALTER SYSTEM SET sga_target = 3072M SCOPE = SPFILE;--for PRD
exec dbms_stats.unlock_schema_stats('SIEBEL');
exec dbms_stats.gather_schema_stats(ownname=>'SIEBEL', cascade=>true);
exec dbms_stats.gather_schema_stats(ownname=>'SIEBEL', method_opt=>'FOR ALL INDEXED COLUMNS SIZE AUTO');
ALTER SYSTEM SET optimizer_use_sql_plan_baselines = TRUE SCOPE=BOTH;
ALTER SYSTEM SET optimizer_capture_sql_plan_baselines = TRUE SCOPE=BOTH;

-- For regular parameters
SELECT name, value
FROM v$parameter
WHERE name IN (
  'optimizer_index_caching',
  'optimizer_mode',
  'query_rewrite_integrity',
  'star_transformation_enabled',
  'cursor_sharing',
  'query_rewrite_enabled'
);
ALTER SYSTEM SET query_rewrite_enabled = FALSE SCOPE = BOTH;
-- For hidden/internal parameters (underscore-prefixed)
SELECT a.ksppinm AS name, b.ksppstvl AS value FROM x$ksppi a, x$ksppcv b WHERE a.indx = b.indx AND LOWER(a.ksppinm) IN ('_always_semi_join','_b_tree_bitmap_plans','_partition_view_enabled','_no_or_expansion','_optimizer_max_permutations');
ALTER SYSTEM SET "_partition_view_enabled" = FALSE SCOPE = BOTH;
ALTER SYSTEM SET "_b_tree_bitmap_plans" = FALSE SCOPE = BOTH;
ALTER SYSTEM SET "_always_semi_join" = OFF SCOPE = BOTH;
ALTER SYSTEM SET "_optimizer_max_permutations" = 100 SCOPE = BOTH;
  ```
  
  If you download oracle instant client you can test connection like this
  
  PS C:\ORCL_TST\instantclient_19_27> .\sqlplus SIEBEL/Welcome1@//<IP address of WSL>:1521/DEV