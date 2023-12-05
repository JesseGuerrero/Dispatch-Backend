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

-Test all security updates<br>
-Create email and username unique on register<br>
-Check if CustomErrorController is needed<br>
<br><br>

#6 Create user authentication part 2<br>
-Create Sign up page for creating customers<br>
-Create Login page with signup link<br>
-Make Spring use Mongo Customers to login<br> 
-Create Forgot password page<br>
-Create Reset password page<br>
<br><br>

#7 Update Customer Repository to extend MongoRepository<br><br>

#8: Create API<br>
-Subscribe endpoint<br>
-Unsubscribe endpoint<br>
-Add tags endpoint<br>
-Add on-schedule end-point<br>
-Add to course endpoint<br><br>

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