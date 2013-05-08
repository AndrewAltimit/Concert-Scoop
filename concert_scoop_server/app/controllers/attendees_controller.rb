class AttendeesController < ApplicationController
  def index
    @concert = Concert.find(params[:concert_id])
    @attendees = @concert.attendees
    
    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @attendees.map { |attendee| attendee.user}.to_json(:only => [:id, :email])}
    end
  end
end
