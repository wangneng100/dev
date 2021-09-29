#!/usr/bin/env python
# -*- coding: utf-8 -*- 

# Importing libraries 
import imaplib, email 
from bs4 import BeautifulSoup

f = open("password.txt", "r")

user = 'wangneng.usa@gmail.com'
password = f.readline()

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

class stock():
    def __init__(self, name):
        self.name=name
        self.buy = 0
        self.sell = 0

f = open("stocklist.txt", "r")
mylist1 = f.readline().split(",")
mylist2 = f.readline().split(",")
mylist3 = f.readline().split(",")
# mylist1 = ['TSLA','AAPL','ADBE','NVDA','TSM','AMZN','MSFT','MA','V','GOOGL','PYPL','LMT']
# mylist2 = ['TDOC','DKNG','JFROG','OPEN','SKLZ','FSLY','PLTR','EXPC','U','PCAR','ONEM']
# mylist3 = ['TCEHY','BIDU','BABA','JD','BILI','DOYU']

print "List1:" + str(mylist1)
print "List2:"+ str(mylist2)
print "List3:" + str(mylist3)

mailData = []
mydictList = []
mylist = []

count = 0
maxCount = 0

keepgo = True

while keepgo:
    
    count = 0
    del mydictList[:]
    del mylist[:]

    days = raw_input('day range:(1)')
    if days=='':
        days = 1
    else:
        days = int(days)

    string_input = raw_input('stock list:(1-bigone, 2-growing, 3-chinese, or list with space split)')
    if string_input=='':
        mylist = mylist1 + mylist2 + mylist3
    elif string_input=='1':
        print "list 1: longtou"
        mylist = mylist1[:]
    elif string_input=="2":
        print "list 2: ark"
        mylist = mylist2[:]
    elif string_input=="3":
        print "list 3: zhongguo"
        mylist = mylist3[:]
    else:
        mylist = string_input.split() #splits the input string on spaces

    print "list len:" + str(len(mylist))

    for x in range(len(mylist)):
        mydictList.append(stock(mylist[x]))

    print('search ' + str(days) + ' days')

    
    if days > maxCount:
        maxCount = days
        mailDataLoaded = [None]*maxCount
        print "loading emails...." + str(maxCount)
        for i in range(latest_email_id,first_email_id,-1):
            
            count = count+1
            if count > maxCount:
                break
            data_tmp = con.fetch(str(i), '(RFC822)' )
            mailDataLoaded[count-1] = data_tmp

    mailData = mailDataLoaded[0:days]

    print "search in emails...."
    for data in mailData:
        for response_part in data:
            arr = response_part[0]
            # print("arr: "+ str(arr))
            if isinstance(arr, tuple):
                msg = email.message_from_string(str(arr[1]))
                # print("msg: "+ str(msg))
                email_subject = msg['subject']
                email_from = msg['from']
                content = msg.get_payload()
                
                for j in range (0,len(mylist)):
                    # if mydictList[j] is None:
                    # print "SEARCH for" + mylist[j]
                    #     mydictList[j] = dict(name=mylist[j], buy=0,sell=0)
                    # if content.find('Buy</td><td>'+mylist[j]) >-1:
                    #     mydictList[j]['buy'] +=1
                    # if content.find('Sell</td><td>'+mylist[j]) >-1:
                    #     mydictList[j]['sell'] +=1
                    
                    soup = BeautifulSoup(content, 'html.parser')

                    tds = soup.find_all("td",string=mylist[j].upper())
                    
                    for td in tds:
                        if td.previous_sibling.previous_sibling:
                            if td.previous_sibling.previous_sibling.string == "Buy":
                                mydictList[j].buy +=1
                            else:
                                mydictList[j].sell +=1
    
    print "+++++++++++++++++++++++"
    for stock_tmp in mydictList:
        if stock_tmp.buy+stock_tmp.buy>0:
            print(stock_tmp.name + " Buy:" + str(stock_tmp.buy))

    print "-----------------------"
    for stock_tmp in mydictList:
        if stock_tmp.sell>0:
            print(stock_tmp.name + " Sell:" + str(stock_tmp.sell))
    print "======================="
    quit = raw_input('Q for quit:')
    if quit == 'q':
        keepgo = False