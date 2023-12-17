# Self-Hosted Email Server Project TODO List

## General Rules:
- First-name is title case.
- Everything else is lowercase.
- Dates are processed as strings and only refer to that day and not the time of day.
- Dates are in the format YYYY-MM-DD.
- Scheduled e-mails are sent at noon.
- Course e-mails are sent at midnight.
- All e-mails are sent in the timezone of the server.
- E-mails missed before the date are sent at the next scheduled time.
- At the end of the course, the course is added as a tag "course course-name" to subscriber.
- Courses in MongoDB are lowercase but are displayed as title case.

## TODO

### 1. Create frontend
- ~~Subscribe to newsletter form~~
- ~~Subscriber list delete/tag-up form~~
- ~~E-mail writing form input~~
- ~~Written e-mails saving form~~
- ~~Broadcast to tag form~~
- ~~Automatic scheduler in front-end~~

### 2. Create subscriber class
- ~~MongoDB Documents~~
- ~~Subscribers collection, subscriber document:~~
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
### 5. Create user authentication with each user being a customer
- ~~Try https://github.com/aliyusahaboadam/Registration-and-Login-Application~~
- ~~Filter homepage, signup & login only clickable by being logged in with Spring security~~
- ~~Make every other page redirect to login~~
- ~~Test all security updates~~
- ~~Create email and username unique on register~~
- ~~Check if CustomErrorController is needed~~

### 6. Create user authentication part 2
- ~~Create Sign-up page for creating customers~~
- ~~Create Login page with signup link~~
- ~~Make Spring use Mongo Customers to login~~
- ~~Test forgot password~~
- ~~Create Forgot password form on the same page as login. Use a separate post request.~~
- ~~Create temporary password on wrong password to email from newsletter~~
- ~~Do a check if the temp password is correct and manually login~~

### 8: Create Newsletter API
- Do all easy endpoints first
    - Subscribe
    - Unsubscribe
    - Broadcast
    - Add empty tag
    - Add delete tag
    - Rename title
    - Rename owner
    - Rename email
    - Rename password
    - Delete newsletter

Test all easy endpoints
- Subscribe
- Unsubscribe
- Add email to tag
- Remove email from tag
- Test custom newsletter email
- Broadcast, create username map
- Add empty tag
- Add delete tag
- Rename title
- Rename owner
- Rename email
- Rename password

### 9. Create a React Typescript frontend for the above API
- Setup React Typescript
- Setup one run for backend and frontend
- Copy ConvertKit frontend for now
- Add basic backend functions to frontend

### 10. Create difficult functionality
1. Delete newsletter
2. Add scheduled e-mail
3. Save and write email MIME
4. Add/update course

### 11: Create automation backend
- Send scheduled e-mails at noon
- Check daily course e-mails at midnight
- Courses follow document stages

### 12 Update to latest
- Update to the latest Gradle
- Update to the latest Spring Boot and Java 21
- Create email list exporter for other services
- Create a backup system
