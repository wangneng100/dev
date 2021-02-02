# Importing libraries 
import imaplib, email 
  
user = 'wangneng100@gmail.com'
password = ''
imap_url = 'imap.gmail.com'
  
# Function to get email content part i.e its body part 
def get_body(msg): 
    if msg.is_multipart(): 
        return get_body(msg.get_payload(0)) 
    else: 
        return msg.get_payload(None, True) 
  
# Function to search for a key value pair  
def search(key, value, con):  
    result, data = con.search(None, key, '"{}"'.format(value)) 
    return data 
  
# Function to get the list of emails under this label 
def get_emails(result_bytes): 
    msgs = [] # all the email data are pushed inside an array 
    for num in result_bytes[0].split(): 
        typ, data = con.fetch(num, '(RFC822)') 
        msgs.append(data)
    return msgs 
  
# this is done to make SSL connnection with GMAIL 
con = imaplib.IMAP4_SSL(imap_url)  
  
# logging the user in 
con.login(user, password)  
  
# calling function to check for email under this label 
con.select('ark')  
  
data = con.search(None, 'ALL')

mail_ids = data[1]
id_list = mail_ids[0].split()   
first_email_id = int(id_list[0])
#print(first_email_id)
latest_email_id = int(id_list[-1])
#print(latest_email_id)
count = 0

#龙头股
mylist = ['TSLA','AAPL','ADBE','NVDA','TSM','AMZN','MSFT','MA','V','GOOGL','PYPL','LMT']

#成长股
#mylist = ['TDOC','DKNG','JFROG','OPEN','SKLZ','FSLY','PLTR']

#中概股
#mylist = ['TCEHY','BIDU','BABA','JD','BILI','DOYU']


days = raw_input('day range:(7)')
if days=='':
    days = 1
else:
    days = int(days)

string_input = raw_input('stock list:(space split)')
if string_input=='':
    mylist = ['TSLA','AAPL','ADBE','NVDA','TSM','AMZN','MSFT','MA','V','GOOGL','PYPL']    
else:
    mylist = string_input.split() #splits the input string on spaces

mydictList = []
for x in range(len(mylist)):
    mydictList.append(dict(name=mylist[x], buy=0,sell=0))

print('search ' + str(days) + ' days')

for i in range(latest_email_id,first_email_id,-1):
    count = count+1
    if count > days:
        break
    data = con.fetch(str(i), '(RFC822)' )
    for response_part in data:
        arr = response_part[0]
        if isinstance(arr, tuple):
            msg = email.message_from_string(str(arr[1]))
            email_subject = msg['subject']
            email_from = msg['from']
            content = msg.get_payload()
            for j in range (0,len(mylist)):
                # if mydictList[j] is None:
                # print "SEARCH for" + mylist[j]
                #     mydictList[j] = dict(name=mylist[j], buy=0,sell=0)
                if content.find('Buy</td><td>'+mylist[j]) >-1:
                    mydictList[j]['buy'] +=1
                if content.find('Sell</td><td>'+mylist[j]) >-1:
                    mydictList[j]['sell'] +=1

for dict in mydictList:
    print(dict['name'] + " Buy:" + str(dict['buy']) + " Sell:" + str(dict['sell']))