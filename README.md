[![](https://jitpack.io/v/hotstu/chartbinding.svg)](https://jitpack.io/#hotstu/chartbinding)
# chartbinding

Android DataBinding wrapper for MPAndroidChart, using chart in a MVVM way!
使用DataBing对MPAndroidChart进行封装， 使用MVVM方式开发图表(饼状图、折线图、饼状图)

the goal of this lib is to making a more simple way to make chart for light charting usage,
avoiding the complex property settings.

<img src="./preview/1.gif" width="400">

## How to use

1. Add the JitPack repository to your build file 
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. Add the dependency
```
	dependencies {
	        implementation 'com.github.hotstu:chartbinding:0.0.1'
	}

```

<br/>

## limit

limited type of charts arte supported(bar/line/pie)

# License

Copyright 2018 hglf

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

<br/>
