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
DataMapper.auto_upgrade!

DataMapper.finalize
enable :sessions

get '/' do 
  
  car = {:make => "bmw", :year => "2003"};
  car.to_json;
end

post '/login' do
	 body_parameters = request.body.read;
  	 params = JSON.parse(body_parameters); 
    @he =Account.first(:uname => params['Username'], :password => params['Password']); 
    session['user']=params['Username'];
    if @he.nil?
    {:uname => 'not success'}.to_json;
    else
    @he.to_json;
    end
end

post '/signup' do
     Account.create(:uname => params[:user_name],:password => params[:pass])
end

post '/' do
	body_parameters = request.body.read;
	data = JSON.parse(body_parameters);
	if data['name']=='Username'
		car = {:name => "postsuccesful"};
	else
		car = {:name => "UNSUCCESS!!!"};
	end
	car.to_json
end

