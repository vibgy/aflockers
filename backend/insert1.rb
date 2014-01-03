require 'rubygems'
require 'sinatra'
require 'json'



get '/' do 
  
  car = {:make => "bmw", :year => "2003"};
  car.to_json;
end

get '/post' do

haml :post
end


post '/insert' do
Intn.create(:nme => params[:ffname],:collge => params[:college],:lnme => params[:lname])
@l2 = params[:name]
end




