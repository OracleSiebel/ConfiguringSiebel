/*globals SIEBEL_BUILD */
SiebelApp.ThemeManager.addTheme(
   "GRAY_ACCORDION",
   {
      css : {
         "sb_theme" : "files/theme-base.css",
         "sc_theme" : "files/theme-gray.css",
         "sn_theme" : "files/theme-nav-accordion.css",
         "sca_them" : "files/theme-calendar.css",
         "dyt_them" : "files/3rdParty/themes/dynatree/skin/ui.dynatree.css"
      },
      objList : [  ]
   }
);
SiebelApp.ThemeManager.addTheme(
   "TANGERINE_ACCORDION",
   {
      css : {
         "sb_theme" : "files/theme-base.css",
         "sc_theme" : "files/theme-tangerine.css",
         "sn_theme" : "files/theme-nav-accordion.css",
         "sca_them" : "files/theme-calendar.css",
         "dyt_them" : "files/3rdParty/themes/dynatree/skin/ui.dynatree.css"
      },
      objList : [  ]
   }
);
//**********************************************************************//
//EXAMPLE OF HOW TO INJECT JAVASCRIPT WITH A THEME CHANGE
//**********************************************************************//
//SiebelApp.ThemeManager.addTheme(
//    "ipad-one-applet-view",
//    {
//      css : {
//        "sb_theme" : "files/theme-base.css"
//      },
//      objList : [ ],
//      reqJs : [ SIEBEL_BUILD + "3rdParty/jswipe.js",  SIEBEL_BUILD + "siebel/oneappletview.js" ]
//    }
//  );
//**********************************************************************//
