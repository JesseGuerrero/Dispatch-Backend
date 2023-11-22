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
-Subscribe to newsletter form<br>
-Subscriber list delete/tag-up form<br>
-E-mail writing form input<br>
-Written e-mails saving form<br>
-Broadcast to tag form<br>
-Automatic scheduler in front-end<br>



#2: Create subscriber class<br>
MongoDB Documents<br>
Subscribers collection, subscriber document:<br>
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
#3: Create API<br>
-Subscribe endpoint<br>
-Unsubscribe endpoint<br>
-Add tags endpoint<br>
-Add on-schedule end-point<br>
-Add to course endpoint<br>
<br><br>

#4: Create automation backend<br>
-Send scheduled e-mails noon<br>
-Check daily course e-mails midnight<br>
-Follow document stages<br>