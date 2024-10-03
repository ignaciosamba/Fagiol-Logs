# findDestinations() looks for inner classes through reflection.
-keepattributes InnerClasses

# NavigationNode are likely all accessed indirectly through findDestinations(), so
# we need to explictly keep them.
-keep,allowobfuscation class * implements com.sambas.fagiollogs.core.navigation.NavigationNodeNew

# findDestinations() needs the INSTANCE field of the NavigationNode objects.
-keepclassmembers class * implements com.sambas.fagiollogs.core.navigation.NavigationNodeNew {
  public static final * INSTANCE;
}