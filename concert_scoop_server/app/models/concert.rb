class Concert < ActiveRecord::Base
  attr_accessible :city, :facebook_page, :name, :start_time, :state, :twitter_tag, :zip
  
  has_many :attendees
  has_many :users, :through => :attendees
end
