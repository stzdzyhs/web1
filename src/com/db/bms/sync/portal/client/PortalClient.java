
package com.db.bms.sync.portal.client;



public class PortalClient {
	
	public String sendMsg(String url, String content) throws Exception{
		return null;
		/*
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			StringEntity entity = new StringEntity(content, "UTF-8");
			post.setEntity(entity);
			
			post.setHeader("Content-Type", "text/json;charset=UTF-8");
			HttpResponse response = httpClient.execute(post);
			HttpEntity responseEnity = response.getEntity();
            InputStreamReader reader = new InputStreamReader(responseEnity.getContent(), "UTF-8"); 
            StringBuffer responseBuffer = new StringBuffer();
            char[] buff = new char[1024]; 
            int length = 0; 
            while ((length = reader.read(buff)) != -1) { 
            	responseBuffer.append(new String(buff, 0, length));
				httpClient.getConnectionManager().shutdown();
            } 
            return responseBuffer.toString();
		}
		catch (Exception e) {
			throw e;
		}
		*/
	}
}
