Dim objXLApp
Dim objXLBook
Dim v_address
Dim v_row
Set   objArgs   =   WScript.Arguments  
For   I   =   0   to   objArgs.Count   -   1  
      WScript.Echo   objArgs(I)
	  If  I = 0 Then
      v_address = objArgs(I) '�ļ���ַ
          Else
      v_row = objArgs(I) '��ӡβ����
	  End If
Next 
Set objXLApp = WScript.CreateObject("Excel.Application")
objXLApp.DisplayAlerts = False
Set objXLBook = objXLApp.Workbooks.Open(v_address)

   objXLBook.Sheets.Item(1).Select
objXLBook.ActiveSheet.PageSetup.PrintTitleRows = v_row 
objXLBook.ActiveSheet.PageSetup.CenterFooter = "��&Pҳ����&Nҳ"
objXLBook.Save
objXLBook.Close
Set objXLBook = Nothing
objXLApp.Quit
Set objXLApp =Nothing
WScript.Quit