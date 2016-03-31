<!-- Template Start: CCAppletList_Tile.swt --> 
<swe:include file="CCApplet_NamedSpacer.swt"/> 
<swe:control id="1100"> 
  <div class="CmdTxt"> 
  <swe:this property="FormattedHtml" hintText="Outside Applet Help Text" hintMapType="Control"/> 
  </div>
</swe:control> 
<swe:form> 
  <swe:include file="CCTitle_Named.swt"/> 
  <div class="swe:this.SelectStyle"> 
    <swe:switch> 
      <swe:case condition="Web Engine State Properties, IsMobileApplicationMode"> 
      <swe:include file="CCListButtonsTop_Mobile.swt"/> 
    </swe:case> 
    <swe:default> 
      <swe:include file="CCListButtonsTop.swt"/> 
    </swe:default> 
  </swe:switch> 
  <swe:error type="Popup"> 
    <div class="swe:class AppletBack"> 
      <div class="error"> 
        <swe:this property="FormattedHtml"/> 
      </div> 
    </div> 
  </swe:error> 
  <div class="AppletHIListBorder siebui-tile-container"> 
    <swe:this property="NoGrid"/> 
    <swe:this property="Horizontal"/> 
    <swe:list> 
      <swe:list-record> 
        <div class="siebui-tile-name"> 
          <swe:for-each count="3" startValue="500" iteratorName="currentId"> 
            <swe:control id="swe:currentId" hintMapType="FormItem"> 
              <div class="siebui-form-data" align="swe:this.TextAlignment"> 
                <swe:this property="FormattedHtml" hintText="Field"/> 
              </div> 
            </swe:control> 
          </swe:for-each> 
        </div> 
        <div class="siebui-tile-details-row1"> 
          <ul> 
            <swe:for-each count="4" startValue="510" iteratorName="currentId"> 
              <li> 
                <swe:control id="swe:currentId" hintMapType="FormItem"> 
                  <div class="siebui-form-data" align="swe:this.TextAlignment"> 
                    <swe:this property="FormattedHtml" hintText="Field"/> 
                  </div> 
                </swe:control> 
              </li> 
            </swe:for-each> 
          </ul> 
        </div> 
        <div class="siebui-tile-image"> 
          <swe:for-each count="2" startValue="520" iteratorName="currentId"> 
            <swe:control id="swe:currentId" hintMapType="FormItem"> 
              <div class="siebui-form-data" align="swe:this.TextAlignment"> 
            <swe:this property="FormattedHtml" hintText="Field"/> 
          </div> 
        </swe:control> 
      </swe:for-each> 
    </div> 
      <div class="siebui-tile-clear"/> 
        <div class="siebui-tile-details-row2"> 
          <ul> 
            <swe:for-each count="4" startValue="530" iteratorName="currentId"> 
              <li> 
                <swe:control id="swe:currentId" hintMapType="FormItem"> 
                    <div class="siebui-form-data" align="swe:this.TextAlignment"> 
                        <swe:this property="FormattedHtml" hintText="Field"/> 
                      </div> 
                    </swe:control> 
                  </li> 
                </swe:for-each> 
              </ul> 
            </div> 
          </div> 
        </swe:list-record> 
      </swe:list> 
    </div> 
  </div> 
</swe:form> 
<!-- Template End: CCAppletList_Tile.swt -->
