# blaze-test

## Iniciando

Siga las siguientes instrucciones para iniciar este proyecto.

### Pre-Requisitos

#### Configuracion de Java:

* Descargar la versión 1.8 del JDk segun el sistema operativo ([lista de JDK](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html))

* Debe contar con la versión 1.8 del JDK ([como instalar el JKD en Linux](https://java.com/en/download/help/linux_x64_install.xml))


* Crear usuario en  [Artifactory](https://innovacionpacifico.jfrog.io/) (apoyarse en devOps).

#### Configuracion de Gradle:

* Debe contar con la version 6.0 o superior de gradle ([como instalar](https://gradle.org/next-steps/?version=6.0&format=bin)).

* Para **Linux**
    * descargar gradle 6
        ```jshelllanguage
        $ wget https://services.gradle.org/distributions/gradle-6.4.1-bin.zip
        ```
    * descomprimir zip
        ```jshelllanguage
        $ sudo unzip -d /opt/gradle gradle-*.zip
        ```
    * configurar variable de entorno ***GRADLE_HOME***

        * crear el archivo **gradle.sh**
            ```jshelllanguage
            $ sudo nano /etc/profile.d/gradle.sh
            ```
        * agregar variable de entorno
            ```jshelllanguage
            $ export GRADLE_HOME=/opt/gradle/gradle-6.4.1
            $ export PATH=${GRADLE_HOME}/bin:${PATH}
            ```
        * hacer el script ejecutable
            ```jshelllanguage
            $ sudo chmod +x /etc/profile.d/gradle.sh
            ```
        * cargar las variables de entorno
            ```jshelllanguage
            $ source /etc/profile.d/gradle.sh
            ```
    * verificar la instalación de gradle
        ```jshelllanguage
        $ gradle -v
        ```
    * Agregar en el archivo **~/.gradle/gradle.properties** (el archivo con las configuraciones repectivas para bajar las dependencias del Artifactory).
        ```jshelllanguage
        $ sudo nano .gradle/gradle.properties
        ```
    * Copiar y agregar la siguiente configuración
        ```properties
        artifactory_user=$USER
        artifactory_password=$ENCRYPTED_PASS
        libs_release_local=https://innovacionpacifico.jfrog.io/innovacionpacifico/libs-release-local
        plugins_release_local=https://innovacionpacifico.jfrog.io/innovacionpacifico/plugins-release-local
        ```
    * Guardar cambios con **CTRL + o**, luego **ENTER**
    * Salir de edición con **CTRL + x**

* Para **Windows**
    * descargar [gradle 6.4.1](https://gradle.org/next-steps/?version=6.4.1&format=bin)

    * abrir una terminal de **PowerShell**

    * crear el directorio **Gradle** en:
        ```powershell
        PS> mkdir C:\Gradle
        ```
    * descomprimir zip
        ```powershell
        PS> Expand-Archive -Path  $HOME"\Downloads\gradle-6.4.1-bin.zip" -DestinationPath "C:\Gradle"
        ```
    * configurar variable de entorno ***GRADLE_HOME***
        * agregar variable de entorno
            ```powershell
            PS> [System.Environment]::SetEnvironmentVariable('GRADLE_HOME', 'C:\Gradle\gradle-6.4.1',[System.EnvironmentVariableTarget]::User)
            PS> [System.Environment]::SetEnvironmentVariable('Path', $env:Path + ';%GRADLE_HOME%\bin', 'User')
            ```
        * abrir una nueva terminar y verificar la instalación de gradle
            ```powershell
            PS> gradle -v
            ```
            
 
### Tener en cuenta
* En el backend iniciar consumineto el sguiente endpoint http://localhost:9050/ms-crud-customer/v1/customer/generate-customer/1000 para poder poblar la base de datos, también se adjunta un archivo JSON si se desea adjuntar desde algun gestor de MongoDB.
* Luego de levantar el servicio del Backend iniciar el front realizando un npm install para las dependencias, desguido de un npm start para iniciar el proyecto
* El proyecto backend ha sido documentado totalmente con Swagger, por lo cual se puede verificar los endpoint que se tienes desde su propia UI.

