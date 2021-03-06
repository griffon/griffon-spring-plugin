/*
 * Copyright 2009-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Andres Almiray
 */

ant.mkdir(dir: "${basedir}/src/spring")

ant.move(file: "${basedir}/src/spring/resources.groovy",
    tofile: "${basedir}/src/spring/springbeans.groovy",
    failonerror: false)
ant.move(file: "${basedir}/src/spring/resources.xml",
    tofile: "${basedir}/src/spring/springbeans.xml",
    failonerror: false)
ant.move(file: "${basedir}/griffon-app/conf/spring/resources.xml",
    tofile: "${basedir}/griffon-app/conf/spring/springbeans.xml",
    failonerror: false)

File springbeans = new File("${basedir}/src/spring/springbeans.groovy")
if (!springbeans.exists()) {
    springbeans.append('''
beans = {

}''')
}

def configFile = new File(basedir, 'griffon-app/conf/Config.groovy')
if (configFile.exists()) {
    def configText = configFile.text
    if (!configText.contains('griffon.services.basic.disabled')) {
        configFile.append '''\ngriffon.services.basic.disabled = true\n'''
    }
}