# marfeel
It's a mini-project that was a task given to me by company I applied to. Nothing fancy.

Goal was to create a basic web crawler that will qualify pages based on the content of title tag.

Requirements:
------
 * expose a rest api that will accept the json document with url of sites
 * for every url crawler will download the page and check if title tag contains required text
 * results of qualification will be persisted in database
 
Technical requirements
-------
 * the business logic should be parallelized
 * spring mvc, spring data, java8, jsoup, maven, junit, spring test, mockito, hamcrest libraries will be used
 * spring contexts will be written manually without spring-boot being used
