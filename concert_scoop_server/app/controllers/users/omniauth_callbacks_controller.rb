class Users::OmniauthCallbacksController < Devise::OmniauthCallbacksController
  
  def twitter
    raise env['omniauth.auth'].to_yaml
  end    
    
  def facebook
    raise env['omniauth.auth'].to_yaml
  end
  private
  
  def redirect_user(user)
    if user.persisted?
      flash.notice = "Signed in!"
      sign_in_and_redirect user
    else
      session["devise.user_attributes"] = user.attributes
      redirect_to new_user_registration_url
    end
  end
end
