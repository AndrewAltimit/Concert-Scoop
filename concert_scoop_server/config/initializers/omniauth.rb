Rails.application.config.middleware.use OmniAuth::Builder do
  # provider :developer unless Rails.env.production?
  provider :twitter, CONFIG[:twitter_key], CONFIG[:twitter_secret]
end