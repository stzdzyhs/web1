Ӧ�ñ���������һ��ѡ�񵯳����ڵ�js����ͨ�������һ������contentWindow.js��win.js��
������֧�֣����л���Ҫһ����������Դ��json.js�ļ������ļ���λ����js/json.js��
���÷�����
	1�� �ڸ�����������win.js
		�ڵ�������������json.js��contentWindow.js.
	2����������ֻҪ����openWin��������������Ϊ(actionName,title,checkedBoxName,placeId,getChannelNoF,getChannelNameF,callbackFun,getSelectChannelsF,dataName)
		actionName:����ҳ���action
		title:����ҳ��ı���
		checkedBoxName:������ĵ�ѡ��checkbox����name
		placeId:��ѡ�������div��id
		getChannelNoF:ȡ�ø����浥ѡ��ĵ�ֵ������������д������дĬ�ϵ���win.js�е�getChannelNo�ķ���
		getChannelNameF��ȡ�ø����浥ѡ��ĵ���ʾ�����ݷ�����������д������дĬ�ϵ���win.js�е�getChannelName�ķ���
		callbackFun���ص�������
		getSelectChannelsF:��ø�ҳ�����ѡԪ��
		dataName:������ݵ�key,Ҳ�����ڶ���Ŀѡ����Ƶ����/Ƶ��
		
		��Win.openWin('business/epgWin.action','��ѡ��Ƶ��',checkedBoxName,placeId,getChannelNoF,getChannelNameF,callbackFun,getSelectChannelsF,dataName)
	3���������棺
		1������$(document).ready(function(){
			//���뷽��
			ContentWindow.register(getChannelNoF,getChannelNameF,checkBoxName);
	  	 	})
	   		����˵����
				getChannelNoF:ȡ�õ���������checkbox�ĵ�ֵ������������д������дĬ�ϵ���ContentWindow.js�е�getChannelNo�ķ���
				getChannelNameF��ȡ�õ�������checkbox����ʾ�����ݷ�����������д������дĬ�ϵ���ContentWindow.js�е�getChannelName�ķ���
				checkedBoxName:��������ĵ�ѡ��checkbox����name
		
		2����ѡ���onclick���������ContentWindow.updateAllChannel(this)
		3����ѡ���onclick���������ContentWindow.updateChannel(this)
		
	4.���·��	
		win.js:    <script src="${path }/js/win/win.js" type="text/javascript"></script>
		json.js��contentWindow.js:   <script src="${path }/js/json.js" type="text/javascript"></script>
									 <script src="${path }/js/win/contentWindow.js" type="text/javascript"></script>
									 
	5.�漰������ֵ��һ����channelNo��channelName
		
		
		