#!/usr/bin/env sh

JAVA_HOME=$(/usr/libexec/java_home)
export ANDROID_SDK_ROOT=~/Library/Android/sdk
export ANDROID_HOME=~/Library/Android/sdk

java -cp gradle/wrapper/gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain "$@"
