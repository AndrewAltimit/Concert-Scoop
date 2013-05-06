class Attendee < ActiveRecord::Base
  attr_accessible :user_id, :concert_id
  belongs_to :user
  belongs_to :concert
end
