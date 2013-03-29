for /D /R %%i in (*.*) do ( 

cd %%i 
if exist .svn ( 
rd .svn /s /q 
) 

if exist CVS ( 
rd CVS /s /q 
) 

cd .. 
) 