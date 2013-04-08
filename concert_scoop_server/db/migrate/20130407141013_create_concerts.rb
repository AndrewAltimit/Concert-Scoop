class CreateConcerts < ActiveRecord::Migration
  def change
    create_table :concerts do |t|
      t.string :name
      t.string :city
      t.string :state
      t.string :zip
      t.datetime :start_time
      t.string :twitter_tag
      t.string :facebook_page

      t.timestamps
    end
  end
end
