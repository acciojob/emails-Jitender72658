package com.driver;

import java.util.*;
import java.text.SimpleDateFormat;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private HashMap<String,Address > hminbox;
    //private HashMap<String,Message > hmtrash;
    private Deque<String> inbox;
    private Stack<String> trash;
    private Stack <String> temp;

    public Gmail(String emailId, int inboxCapacity) {
              super(emailId);
             this.inboxCapacity = inboxCapacity;
            hminbox = new HashMap<>();
            inbox = new LinkedList<String>();
            trash = new Stack<>();
           // hmtrash = new HashMap<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
            if(inbox.size()>getInboxSize()){
                trash.push(findOldestMessage());
                hminbox.remove(findOldestMessage());
                inbox.removeLast();
            }
            hminbox.put(message, new Address(date,sender));
            inbox.addFirst(message);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
         if(hminbox.containsKey(message)){
             temp = new Stack<>();
             while(inbox.size()>0 && !inbox.peekFirst().equals(message)){
                 temp.push(inbox.removeFirst());
             }
             String copy = inbox.removeFirst();
            // hmtrash.put(copy,hminbox.get(copy));
             hminbox.remove(copy);
             trash.push(copy);
             while(temp.size()>0){
                 inbox.add(temp.pop());
             }
         }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
          if(inbox.size()==0) return null;
          else return inbox.peekFirst();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size()==0) return null;
        else return inbox.peekLast();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for (Iterator itr = inbox.iterator();
             itr.hasNext();) {
           String s = (String)itr.next();
           Address m = hminbox.get(s);
           if(m.date.compareTo(start)>=0 && m.date.compareTo(end)<=0){
               count++;
           }
        }
          return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
              return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
            return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
         while(trash.size()>0) {
             trash.pop();
         }
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
    class Address{
        String sender;
        Date date;
       Address(Date date,String sender ){
            this.date = date;
            this.sender = sender;
        }
    }
}

