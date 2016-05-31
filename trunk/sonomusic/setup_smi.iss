; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "SONOMUSIC"
#define MyAppVersion "1.5"
#define MyAppPublisher "SONOMUSIC"
#define MyAppURL "http://www.example.com/"
#define MyAppExeName "updater_smi.jar"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{457F9042-BA48-42AB-936F-54FCA29EEEC7}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={pf}\{#MyAppName}
DefaultGroupName={#MyAppName}
OutputDir=..\Ejecutable
OutputBaseFilename=setup_smi
Compression=lzma
SolidCompression=yes

[Languages]
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked
Name: "quicklaunchicon"; Description: "{cm:CreateQuickLaunchIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked; OnlyBelowVersion: 0,6.1

[Files]
Source: "updater_smi.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "reports\*"; DestDir: "{app}\reports"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "uploads\*"; DestDir: "{app}\uploads"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "almacen.txt"; DestDir: "{app}"; Flags: ignoreversion
Source: "logo.ico"; DestDir: "{app}"; Flags: ignoreversion
Source: "logo.png"; DestDir: "{app}"; Flags: ignoreversion
Source: "logo_sonomusic.png"; DestDir: "{app}"; Flags: ignoreversion
Source: "server.txt"; DestDir: "{app}"; Flags: ignoreversion
Source: "sonomusic.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "version.txt"; DestDir: "{app}"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{group}\{cm:ProgramOnTheWeb,{#MyAppName}}"; Filename: "{#MyAppURL}"
Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\Sono Music Import - POS"; Filename: "{app}\{#MyAppExeName}";  IconFilename: "{app}\logo.ico" ; Tasks: desktopicon
Name: "{userappdata}\Microsoft\Internet Explorer\Quick Launch\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: quicklaunchicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: shellexec postinstall skipifsilent
