# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)

users = User.create([{email: 'alice@example.com', provider: 'facebook', uid: 12345},
  {email: 'bob@example.com', provider: 'facebook', uid: 52345},
  {email: 'chelsey@example.com', provider: 'facebook', uid: 62345},
  {email: 'dan@example.com', provider: 'facebook', uid: 72345},
  {email: 'erin@example.com', provider: 'twitter', uid: 82345},
  {email: 'fred@example.com', provider: 'twitter', uid: 92345},])

concerts = Concert.create([{name: 'blink182', city: 'Rock Island', state: 'IL', zip: '61201', start_time: 5.days.from_now},
 {name: 'Iron Maiden', city: 'Rock Island', state: 'IL', zip: '61201', start_time: 9.days.from_now},
 {name: 'Modest Mouse', city: 'Rock Island', state: 'IL', zip: '61201', start_time: 14.days.from_now},
 {name: 'Rise Against', city: 'Rock Island', state: 'IL', zip: '61201', start_time: 17.days.from_now},
 {name: 'Taylor Swift', city: 'Rock Island', state: 'IL', zip: '61201', start_time: 30.days.from_now},
 {name: 'Katy Perry', city: 'Rock Island', state: 'IL', zip: '61201', start_time: 40.days.from_now},
  ])
