An open-source email server for your self-hosting needs. <br><br>

General rules:<br>
*First-name is title case<br>
*everything else is lower case<br>
*dates are processed as strings and only refer to that day and not the time of day.<br>
*dates are in the format YYYY-MM-DD<br>
*Scheduled e-mails are sent at noon<br>
*Course e-mails are sent at midnight<br>
*All e-mails are sent in the timezone of the server<br>
*E-mails missed before the date are sent at the next scheduled time<br>
*At the end of the course, the course is added as a tag "course course-name" to subscriber<br>
*Courses in mongo are lower case but are displayed as title case<br>
<br>

TODO<br>
#1: Create frontend<br>
<s>-Subscribe to newsletter form</s><br>
<s>-Subscriber list delete/tag-up form</s><br>
<s>-E-mail writing form input</s><br>
<s>-Written e-mails saving form</s><br>
<s>-Broadcast to tag form</s><br>
<s>-Automatic scheduler in front-end</s><br>



#2: Create subscriber class<br>
<s>MongoDB Documents</s><br>
<s>Subscribers collection, subscriber document:</s><br>
```JSON
{
  "name": "",
  "username": "",
  "email": "",
  "password": "",
  "email-subscribers": {
    "email-example@gmail.com": {
      "first-name": "John"
    }
  },
  "tags": {
    "tag-example": [
      "email-example@gmail.com",
      "john@gmail.com",
      "jane@gmail.com"
    ]
  },
  "scheduled": [
    {
      "date": "2020-01-01",
      "tag": "tag-example",
      "subject": "Subject",
      "body": "Body"
    },
    {
      "date": "2020-01-08",
      "tag": "tag-example",
      "subject": "Subject",
      "body": "Body"
    }
  ],
  "daily-courses": [
    {
      "course-name1": {
        "emails": [
          {
            "subject": "Subject",
            "body": "Body"
          },
          {
            "subject": "Subject",
            "body": "Body"
          }
        ],
        "stages": {
            "1": [
              "John@gmail.com",
              "Jane@gmail.com"
            ],
            "2": [
              "Peter@gmail.com",
              "Joe@gmail.com"
            ]
        }
      }
    },
    {
      "course-name2": {
        "emails": [
          {
            "subject": "Subject",
            "body": "Body"
          },
          {
            "subject": "Subject",
            "body": "Body"
          }
        ],
        "stages": {
          "1": [
            "John@gmail.com",
            "Jane@gmail.com"
          ],
          "2": [
            "Peter@gmail.com",
            "Joe@gmail.com"
          ]
        }
      }
    }
  ],
  "pre-written_emails": {
    "template-name1": {
      "subject": "Subject line",
      "body": "E-mail formatted body"
    },
    "template-name2": {
      "subject": "Subject line",
      "body": "E-mail formatted body"
    }
  }
}
```
<br><br>

#5 Create user authentication with each user being a customer<br>
<s>-Try https://github.com/aliyusahaboadam/Registration-and-Login-Application </s><br>
<s>-Filter homepage, signup & login only clickable by being logged in with Spring security</s><br>
<s>     -Make every other page redirect to login</s><br><br>

<s>-Test all security updates</s><br>
<s>-Create email and username unique on register</s><br>
<s>-Check if CustomErrorController is needed</s><br>
<br><br>

#6 Create user authentication part 2<br>
<s>-Create Sign up page for creating customers</s><br>
<s>-Create Login page with signup link</s><br>
<s>-Make Spring use Mongo Customers to login</s><br> 
<s>-test forgot password</s><br>
<s>-Create Forgot password form on same page as login. Use a seperate post request.</s><br>
<s>-Create temporary password on wrong password to email from newsletter</s><br>
<s>-Do a check if temp password is correct and manually login</s><br>
<br><br>

#8: Create Newsletter API<br>
-Do all easy endpoints first<br>
<s>1. Subscribe</s><br>
<s>2. Unsubscribe</s><br>
<s>3. Broadcast</s><br>
<s>4. Add empty tag</s><br>
<s>5. Add delete tag</s><br>
<s>6. Rename title</s><br>
<s>7. Rename owner</s><br>
<s>8. Rename email</s><br>
<s>9. Rename password</s><br>
<s>10. Delete newsletter</s>
<br><br>

Test all easy endpoints<br>
<s>1. Subscribe</s><br>
<s>2. Unsubscribe</s><br>
3. Add email to tag<br>
4. Broadcast<br>
5. Add empty tag<br>
6. Add delete tag<br>
7. Rename title<br>
8. Rename owner<br>
9. Rename email<br>
10. Rename password<br>
11. Delete newsletter
<br><br>

-Difficult<br>
1. Add scheduled e-mail<br>
2. Save and write email<br>
3. Add/update course
<br><br>

#9: Create automation backend<br>
-Send scheduled e-mails noon<br>
-Check daily course e-mails midnight<br>
-Follow document stages<br><br>

#10 Update to latest<br>
-Update to latest Gradle<br>
-Update to latest Spring boot and Java 21<br>
-Create email list exporter for other services<br>
-Create backup system<br>

change user to site,
change last name to site name,
keep first name for emails, lower case then title each word in site name,
check unique site name only