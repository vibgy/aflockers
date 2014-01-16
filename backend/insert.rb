require 'rubygems'
require 'data_mapper'
require 'sinatra'
require 'json'
require 'dm-mysql-adapter'
      
DataMapper::Logger.new($stdout, :debug)

DataMapper.setup(:default, "mysql://root:winner@localhost/deenbandhu")

DataMapper::Property.auto_validation(false)
DataMapper::Property.required(false)
					   
class Account 
	include DataMapper::Resource
	property :id, 	Serial 	# Required in all DM classes coming from d
	property :uname, String, :required => true
	property :password, String, :required => true
end

class Event
       include DataMapper::Resource
       property :id, 	Serial 	# Required in all DM classes coming from d
       property :ename, String, :required => true
       property :verb, String
       property :activity, String
       property :date, Date
       property :time, Time
       property :place, String
       property :organizer, String
       property :fees,Integer
       property :prize, Integer
       property :description,String
end

class Participate
       include DataMapper::Resource
       property :id, 	Serial 	# Required in all DM classes coming from d
       property :ename, String, :required => true
       property :member, String

end
DataMapper.auto_upgrade!

DataMapper.finalize
enable :sessions

post '/login' do 
    @he =Account.first(:uname => params[:user_name], :password => params[:pass]); 
    session['user']=params[:user_name];
    if @he.nil?
     return {:status => 'not success'}.to_json;
    else
    @he.to_json;
    end
end

get '/'do
	car=Event.all;
	car.to_json;
end

post '/activity' do
	body_parameters = request.body.read;
	data = JSON.parse(body_parameters);
	activity=Event.all(:verb => data['name'])
	car = {:make => activity};
	car.to_json
end

post '/event' do
	body_parameters = request.body.read;
	data = JSON.parse(body_parameters);
	event=Event.all(:activity => data['name'])
	car = {:make => event};
	car.to_json
end

post '/CreateEvent' do
	body_parameters = request.body.read;
	data = JSON.parse(body_parameters);
	Event.create(:ename => data['Ename'],:verb => data['Verb'],:activity => data['Activity'],:date => data['Date'],:time => data['Time'],:place => data['Place'],:organizer => "astha",:fees => data['Fees'],:prize => data['Prize'],:description =>data['Description']);
end

post '/eventDetail' do
	body_parameters = request.body.read;
	data = JSON.parse(body_parameters);
	hm=Event.first(:ename => data['name'])
	hm.to_json
end

post '/delete' do
	body_parameters = request.body.read;
	data = JSON.parse(body_parameters);
	ename= Event.first(:ename => data['name'])
        ename.destroy
end

get '/show' do
        car=Event.all(:organizer => "astha")
	car.to_json;
end

post '/search' do
	body_parameters = request.body.read;
	data = JSON.parse(body_parameters);
	hm=Event.first(:ename => data['name'])
	hm.to_json
end

post '/participate' do
	body_parameters = request.body.read;
	data = JSON.parse(body_parameters);
	Participate.create(:ename => data['name'],:member => "astha")

end
