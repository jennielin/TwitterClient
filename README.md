# Twitter App

This is a simple Twitter client app powered by the Twitter RESTful API.  Please
 see *CodePath* Android Observer Group [Week 3](http://courses.codepath.com/courses/intro_to_android/week/3#!module) material, covering networking, authentication and persistence.


## User Stories

* [x] User can sign in to Twitter using OAuth login
* [x] User can view the tweets from their home timeline
* [x] User should be displayed the username, name, and body for each tweet
* [x] User should be displayed the relative timestamp for each tweet "8m", "7h"
* [x] User can view more tweets as they scroll with infinite pagination
* [x] Optional: Links in tweets are clickable and will launch the web browser (see autolink)
* [x] User can compose a new tweet
* [x] User can click a “Compose” icon in the Action Bar on the top right
* [x] User can then enter a new tweet and post this to twitter
* [x] User is taken back to home timeline with new tweet visible in timeline
* [x] Optional: User can see a counter with total number of characters left for tweet


![GIF Walkthrough]()

GIF created with [LiceCap](http://www.cockos.com/licecap/).


## Libraries

This app leverages a few third-party libraries:

 * [Android AsyncHTTPClient](http://loopj.com/android-async-http/) - For asynchronous network requests
 * [Picasso](http://square.github.io/picasso/) - For remote image loading

## Development Environment

 * Android Studio 1.0.1

 * App tested with BlueStacks (4.4.2 support) on Windows 7

## Before cloning this repository


Like with any API, you have to register before you can use it, i.e. registering
developer account to get a Twitter application consumer key and a consumer secret.

Click "Create New App" at apps.twitter.com.
Make sure a Callback URL is entered, modify the settings to allow sign in, and choose Read and Write permission.


## Learning Steps

* [Twitter REST APIs](https://dev.twitter.com/rest/public) on IDs.
	* Problem: Twitter timelines are constantly having new Tweets added to their front.
	* Solution: cursoring, read the timeline relative to the IDs of Tweets it has already processed.  First endpoint request has count only, then keep track the lowest ID received -1 as max_id.  Maintain highest since_id (non inclusive) ...

* Code based on [android rest client template] (https://github.com/codepath/android-rest-client-template)
*  OAuth login

### final
What is "final" keyword in Java? http://stackoverflow.com/questions/15655012/how-final-keyword-works

The final keyword can be interpreted in two different ways depending on what it's used on:

Data types: For ints, doubles etc, it will ensure that the value cannot change,

Reference types: For references to objects, final ensures that the reference will never change, meaning that it will always refer to the same object. It makes no guarantees whatsoever about the values inside the object being referred to staying the same.

As such, final List foo; ensures that foo always refers to the same list, but said list may change over time.


This is a very good interview question. Sometimes they might even ask you what is the difference between a final object and immutable object.

1) When someone mentions a final object, it means that the reference cannot be changed, but its state(instance variables) can be changed.

2) An immutable object is one whose state can not be changed, but its reference can be changed. Ex:

    String x = new String("abc"); 
    x = "BCG";
ref variable x can be changed to point a different string, but value of "abc" cannot be changed.

3) Instance variables(non static fields) are initialized when a constructor is called. So you can initialize values to you variables inside a constructor.

4) "But i see that you can change the value in the constructor/methods of the class". -- You cannot change it inside a method.

5) A static variable is initialized during class loading. So you cannot initialize inside a constructor, it has to be done even before it. So you need to assign values to a static variable during declaration itself.


### Instance
http://java67.blogspot.com/2014/11/difference-between-instance-and-object-in-java.html
http://math.hws.edu/javanotes/c5/s1.html

Instance is memory allocation on the ram at particular moment. So if you think about instance you should immediately ask the question that instance of what? if instance is for class then it object. So in memory first instance is created and then object is created.
