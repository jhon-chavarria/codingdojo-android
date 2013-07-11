##### How to run?

    node app.js

##### How to change you're domain

    config.js replace nodejs.clov3r.net to you're domain

##### How to change port
    app.js replace 4000 to you're port



##### How to send data
    
    Url: /api/post
    Params: name, email, message

##### How to get data
    
    Url: /api/get
    Response:
    [
        {
            "id":"51dddb458f55bde044000003",
            "name":"demo",
            "email":"demo@demo.com",
            "message":"Hola mundo",
            "registered":"2013-07-10T22:08:05.666Z"
        }
    ]

##### How to insert example data

    Url: /api/demo
