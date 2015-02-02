# APIservice-Sample
Sample Boilerplate code for Any API service with OkHttp + Retrofit
This example show simple way to create a client for any endpoint (This uses github)
##To set use in your own endpoint 
  1.  Set your end point url in 
  ```java
  
  public static final String WEB_API_ENDPOINT = "https://api.github.com/"
  
  ```
  2. Create your Models 
  3. Write your REST API in retrofit
  4. That's it Now you can start using
  
##Usage
  1. Create Your Apllication class and add the following line
     >You can set Cache dir,Cache Size and Cache Duration
     
  ```java
  
  ServiceApi.initialize(this,
                new ClientConfig.Builder()
                        .setCacheDir(getCacheDir())
                        .setcachedSize(10 * 1024 * 1024)
                        .setcacheExpire(60 * 60 * 24 * 28)
                        .build()
        );
  
  ```
  2. You can Start using like below
  
   1. For cached Request
      ```java
      
      ServiceApi.getInstance().getCachedApiService().getRepositories(new Callback<List<Repo>>() {
                @Override
                public void success(List<Repo> repos, Response response) {
                    adapter = new ListAdapter(ListActivity.this, repos);
                    listView.setAdapter(adapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    

                }
            });
      
      ```
    2. For Normal Request
      ```java
      
      ServiceApi.getInstance().getApiService().getRepositories(new Callback<List<Repo>>() {
                @Override
                public void success(List<Repo> repos, Response response) {
                    adapter = new ListAdapter(ListActivity.this, repos);
                    listView.setAdapter(adapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    

                }
            });
      
      ```
      
##Thanks to 
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* Special Thanks  to (https://github.com/Drivemode/SpotifyApi) Inspired from

