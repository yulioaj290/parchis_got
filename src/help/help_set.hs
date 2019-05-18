<?xml version="1.0" encoding='ISO-8859-1' ?>
<!DOCTYPE helpset
PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN"
"http://java.sun.com/products/javahelp/helpset_1_0.dtd">
<helpset version="1.0">
   <title>JavaHelp Example</title>
   <maps>
      <!-- Default page when show the help -->
      <homeID>aplication</homeID>
      <!-- What map we wish? -->
      <mapref location="map_file.jhm"/>
   </maps>

   <!-- The view we want to show in the help -->
   <!-- Content table tag -->
   <view>
      <name>Content Table</name>
      <label>Content Table</label>
      <type>javax.help.TOCView</type>
      <data>toc.xml</data>
   </view>

   <!-- Index tag -->
   <view>
      <name>Index</name>
      <label>Index</label>
      <type>javax.help.IndexView</type>
      <data>indice.xml</data>
   </view>

   <!-- Search tag -->
   <view>
      <name>Search</name>
      <label>Search</label>
      <type>javax.help.SearchView</type>
      <data engine="com.sun.java.help.search.DefaultSearchEngine">
         JavaHelpSearch
      </data>
   </view>

</helpset> 
