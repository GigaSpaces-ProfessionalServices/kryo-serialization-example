The <b>kryo-example</b> project provides an example of serialization of an embedded object using kryo while persisting a space object.

The Chrome Postman extension can be used for testing the service URLs. 

It is available from https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en.

Example calls are as follows:

<table>
	<tr>
		<th>URL</th>
		<th>Request Body</th>
	</tr>
	<tr>
		<td>http://localhost:8093/shoppingcart-rest/cart/101</td>
		<td>{'id':101,'user':'sudip','shippingAddress':{'street':'beekman road','city':'kendall park','country':'usa','type':'residence'},'billingAddress':{'street':'5th avenue','city':'new york','country':'usa','billedParty':'gigaspaces'},'prize':{'name':'christmas surprise','amount':100},'items':[{'name':'book','count':1,'discounts':[{'name':'d1','amount':5},{'name':'d2','amount':2}]},{'name':'toy','count':4,'discounts':[{'name':'d1','amount':7},{'name':'d2','amount':4}]}]}</td>
	</tr>
	<tr>
		<td>http://localhost:8093/shoppingcart-rest/updatePaymentData/101</td>
		<td>{'cardNumber':'1234987613579751','holderName':'Sudip Bhattacharya','validUpto':'07/20','cvvCode':147}</td>
	</tr>
</table>

The files <b>asm-5.0.4.jar</b>, <b>kryo-4.0.0.jar</b>, <b>minlog-1.2.jar</b>, <b>objenesis-2.4.jar</b> and <b>reflectasm-1.11.3.jar</b> need to be put on the <b>platform/ext</b> sub-folder of the <b>lib</b> folder in the XAP distribution.

It is possible to refer to a key inside a dynamic property field in a query. For example, the following queries work fine:
 
<pre>
select uid,* from com.gigaspaces.kryo_example.common.Cart WHERE paymentData.info.holderName = 'Sudip Bhattacharya'

select uid,* from com.gigaspaces.kryo_example.common.Cart WHERE paymentData.info.holderName in ('Sudip Bhattacharya', 'Rob Ray')
</pre>



