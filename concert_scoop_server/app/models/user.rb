class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :token_authenticatable, :confirmable,
  # :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable
  devise :omniauthable, :omniauth_providers => [:twitter]

  # Setup accessible (or protected) attributes for your model
  attr_accessible :email, :password, :password_confirmation, :remember_me
  attr_accessible :provider, :uid
  
  def self.find_for_twitter_oauth(omniauth)
    authentication = Authentication.find_by_provider_and_uuid(omniauth['provider'], omniauth['uid'])
    if authentication && authentication.user
      authentication.user
    else
      user = User.create!(:email => omniauth['email'], :provider => omniauth['provider'], :uuid => omniauth['uid'])
      user.save
      user
    end
  end


def password_required?
  super && provider.blank?
end

def update_with_password(params, *options)
  if encrypted_password.blank?
    update_attributes(params, *options)
  else
    super
  end
end


end
