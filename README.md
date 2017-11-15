CodeExceptionFactory
====================
遇到的异常信息太多，没办法统一处理显示为可视化的异常信息？
多语言下可视化异常信息变得难以处理？
试试用CodeExceptionFactory 统一异常处理，优化界面异常提示信息吧！
Download
--------------
Use SNAPSHOT in mavenCentral
```groovy
repositories {
        maven {
           url 'https://oss.sonatype.org/content/repositories/snapshots/'
        }
    }

dependencies {
      implementation 'com.github.lauhwong:code-exception-utils:0.1-SNAPSHOT'
    }
```
你可以按照下面 的步骤使用该库文件。
```java
//install first.
CodeInstaller.newInstaller()
                .addExceptionCode("http", R.array.http)
                .codeBitLimit(4)
                .install(getApplicationContext());
//invoke example
String[] mCodes = {"1000", "1001", "1002", "1003"};
public void showException() {
    int index = new Random().nextInt(4);
    CodeException codeException = CodeExceptionFactory.create("http", mCodes[index]);
    String localizedMessage = codeException.getLocalizedMessage();
    Log.d(getClass().getSimpleName(), localizedMessage);
}
```
每个code对应一个错误描述信息。在资源文件中定义为如下的结果：
```xml
 <!--define exceptions list-->
     <array name="http">
         <item>1000: 无法链接到服务器!</item>
         <item>1001:今天天气不好，服务器罢工了!</item>
         <item>1002:手机没吃饱，拒绝工作!</item>
         <item>1003: 风太大，请求失败!</item>
     </array>
```

License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
