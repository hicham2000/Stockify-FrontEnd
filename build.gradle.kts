buildscript {
    val agp_version by extra("4.0.0")
    val agp_version1 by extra("7.2.0")
    val agp_version2 by extra("8.1.1")
    val agp_version3 by extra("8.1.1")
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id ("org.sonarqube") version "4.4.1.3373"
    id("com.google.gms.google-services") version "4.4.0" apply false
}











