Android开发全局常用工具整理

1 activity fragment常用基类
2 常用的工具类
3 常用并且通用的控件
4 权限的判断
5 全局样式
6 注解类的使用
7 常用的颜色
8 常用的控件 文字 大小的 dp sp值
9 butterknife 如果是kontlin dataBinding 则不需要使用此库
Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Add the dependency
  
  dependencies {
	        implementation 'com.github.bamboolife:CommonLibrary:1.0.0'
	}
```	
