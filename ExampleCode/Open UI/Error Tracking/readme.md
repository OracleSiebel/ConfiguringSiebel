#Error Tracking

Console errors are errors reported within the browser environment, and which are typically not captured in production Siebel environments. They can be caused by a wide range of issues, ranging from issues with CSS, bugs in delivered JS or delivered 3rd party libraries, and of course issues with custom renderers, models, and plugin wrappers. Capturing them silently, without disturbing the user, is a useful way to keep track of issues as they occur, for whatever reason, allowing development teams to review them, along with salient additional data that could aid reproduction of the issue.

In this section, we'll provide code to help you integrate with some of the popular error tracking applications available in the cloud.

#####Bugsnag

The initial integration presented here is with [bugsnag](https://bugsnag.com). For more details, [click here](Bugsnag Integration).