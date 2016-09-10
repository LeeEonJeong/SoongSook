package com.haniumpkg.myapp;

public class FriendDAO {
	   private String user_key;
	   
	   public void addFriend(){
		   //curl -XPOST 'https://:your_server_url/friend' -d '{"user_key" : "HASHED_USER_KEY" }'		   
	   }
	   
	   public void deleteFriend(){
		   //curl -XDELETE 'https://:your_server_url/friend/:user_key'
	   }
}
