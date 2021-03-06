# SearchInJenkinsLogs
A tool to be run for a Jenkins job to search a text with regular expressions in build artifacts.

## Benefits:
- searches matching a regular expression with each build artifact file content
- also, with -DsearchInJUnitReports=true you can search only in tests failures and print the failed test reports
- another important feature is that setting -DgroupTestsFailures=true will find the common similar tests failures with a maximum difference threshold set with -DdiffThreshold argument. 
- showing the failed tests difference, for the build specified in -Dbuilds, is also possible setting -DshowTestsDifferences=true and -DreferenceBuilds=$BuildNumbers, the builds with the tests results to be compared with. The results will contain the failed test URL, the failure message and the reference build failure message. 
- the job builds where to search can be specified as an enumeration and range using -Dbuilds property, or the last builds using -DlastBuildsCount property
- the artifacts files can be filter using regular expressions and more filters could be applied separated with commas ","
- parallel processing, searches the regular expression for each build node in a separated thread for a better performance
- the thread count can be specified in -DthreadPoolSize property
- has disk backup support for saving artifacts for the Jenkins job (-DbackupJob=true and -DbackupPath=$path). Also, you can search in backup files instead of querying Jenkins API (-DuseBackup=true and -DbackupPath=$path), or remove the backup for specified builds (-DremoveBackup=true and -DbackupPath=$path)
- because it's done in Java it works cross platform for Windows, Unix, OS X Jenkins applications

## Usage e.g:
Change the path to out/artifacts/searchinjenkinslogs_jar/ directory and run:

java -DjobUrl="https://jenkins.com/job/JobName/" -DnewUrlPrefix="http://jenkins-proxy.com/job/JobName/" -DthreadPoolSize=20 -Dbuilds="112,114-116,118" -DlastBuildsCount=2 -DartifactsFilters=".* fileFilter1.* ,.* fileFilter2.* " -DsearchInJUnitReports=true -DsearchedText=".* textToFind.* " -DgroupTestsFailures=true -DdiffThreshold=10 -DuseBackup=true -DbackupPath=$backupPath -cp searchinjenkinslogs.jar Main

java -DjobUrl="https://jenkins.com/job/JobName/" -DnewUrlPrefix="http://jenkins-proxy.com/job/JobName/" -DartifactsFilters=".* fileFilter1.* ,.* fileFilter2.* " -DbackupJob=true  -DbackupPath=$backupPath -cp searchinjenkinslogs.jar Main

java -DjobUrl="https://jenkins.com/job/JobName/" -DnewUrlPrefix="http://jenkins-proxy.com/job/JobName/"  -DartifactsFilters=".*xml " -DuseBackup=true  -DbackupPath=$backupPath -DjobUrl2="https://jenkins.com/job/$ReferenceJobName/" -DnewUrlPrefix2="http://jenkins-proxy.com/job/$ReferenceJobName/" -Dbuilds=$BuildToCompare -DreferenceBuilds=$ReferenceBuilds -DdiffThreshold=$DiffThreshold -DshowTestsDifferences=true -cp searchinjenkinslogs.jar Main

- you could use it to find crashes or exceptions that appear in the app logs while running the UI tests. 
For iOS app system logs, you could use this regular expression: -DsearchedText="(.\*Terminating app due to uncaught exception.\*)|(.\*${AppName}\\[[0-9]\*\\]: fatal error:.\*)" , where ${AppName} is the name of the tested iOS app.

## IDE:
Easy to integrate the project with Intellij, JDK 8

## TODO:
Generate a HTML report.

## Please, give a star to this project if it helps you.

## Contact
[Profile](http://nacuteodor.wix.com/profile)

## Related projects:
https://github.com/nacuteodor/ProcessTestSummaries
