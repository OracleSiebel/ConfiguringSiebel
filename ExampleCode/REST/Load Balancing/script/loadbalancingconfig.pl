use strict;
use warnings; 

use XML::Simple;
use Data::Dumper;

my $num_args = $#ARGV + 1;
if ($num_args != 3) {
    print "\nPlease enter valid arguments\n";
    exit;
}

my $homedir = $ARGV[0];
my $httpdHome=$ARGV[1];
my $hostname=$ARGV[2];
my $workersfilepath = "$httpdHome\\workers.properties";
my $javacontainerlist='_';
my $nodes='jvm';
my $port=0;

opendir(MYDIR, $homedir) or die "\nUnable to open dir $homedir\n";
my @javacontainerfolders = grep{/^javacontainer/} readdir(MYDIR);

my %commonworkerproperties=( 'lbfactor',1,'socket_keepalive',1,'socket_timeout', 300);

# Use the open() function to create the file.
unless(open FILE, '>'.$workersfilepath) {
    # Die with error message 
    # if we can't open it.
    die "\nUnable to create $workersfilepath\n";
}

# Write into workers.properties file.

# Define workers 
print FILE "# Define workers\n";
print FILE "worker.list=loadbalancer,status\n";

# Set properties for javacontainer1,javacontainer2,etc
foreach my $javacontainer (@javacontainerfolders) {

   if($javacontainer ne "javacontainer0")
   {
      
   # find AJP port number from Ssever.xml file.
   my $xmlfilepath="$homedir\\$javacontainer\\conf\\server.xml";
   my $parser  = new XML::Simple;
   my $doc    = $parser->XMLin($xmlfilepath);
   my $xc = $doc->{Service};
   
   foreach my $sections (@{$xc->{'Connector'}}) {
    my $protocol = $sections->{protocol};
	
	if($protocol eq 'AJP/1.3')
	{
	  $port=$sections->{port};    
	 
	}
	
    } 	

	$nodes = $xc->{Engine}->{jvmRoute};
	
	if($javacontainerlist eq '_')
	{
	$javacontainerlist= $nodes;
	}
	else{
	$javacontainerlist="$javacontainerlist,$nodes";
	}

     
   print FILE "\n# Set properties for $nodes\n";
   my %workerproperties = ('type','ajp13', 'host', $hostname, 'port', $port);
   while ((my $key, my $value) = each %workerproperties) {
		print FILE "worker.$nodes.$key = $value\n";
	}
   while ((my $key, my $value) = each %commonworkerproperties) {
		print FILE "worker.$nodes.$key = $value\n";
	}
	}
}

# Set properties for loadbalancer
my %loadbalancerdata = ('type','lb', 'balance_workers', $javacontainerlist, 'sticky_session', 'true', 'sticky_session_force','false');
print FILE "\n# Set properties for loadbalancer\n";
while ((my $key, my $value) = each %loadbalancerdata) {
		print FILE "worker.loadbalancer.$key = $value\n";
}

# Get statistics
print FILE "\n# Get statistics\n";
print FILE "worker.status.type=status\n";

# close the file.
close FILE;
close MYDIR;

print "Workers.properties file creation completed.\n";

# Add Mod_jk module in httpd.conf file
my $confilepath="$httpdHome\\httpd.conf";
# Use the open() function to open the file.
unless(open FILE, '>>'.$confilepath) {
    # Die with error message 
    # if we can't open it.
    die "\nUnable to open $confilepath\n";
}

# Write into httpd.conf file.
print FILE "\n# Load the mod_jk module\n";
print FILE "LoadModule jk_module modules/mod_jk.dll\n";

print FILE "\n<IfModule jk_module>\n";
print FILE "JkWorkersFile conf/workers.properties\n";
print FILE "JkLogFile <logfile path>\n";
print FILE "JkLogStampFormat \"[%b %d %Y - %H:%M:%S]\"\n";
print FILE "JkRequestLogFormat \"%w %V %T\" \n";
print FILE "JkLogLevel <logvalue>\n";
print FILE "JkMount /siebel-rest loadbalancer\n";
print FILE "JkMount /Jkmanager status\n";
print FILE "</IfModule>\n";

# close the file.
close FILE;












