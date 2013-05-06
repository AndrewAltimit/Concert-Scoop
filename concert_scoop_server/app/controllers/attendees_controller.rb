class AttendeesController < ApplicationController
  def index
    @concert = Concert.find(params[:concert_id])
    @attendees = @concert.attendees
    
    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @attendees }
    end
  end
end
