class ConcertsController < ApplicationController
  protect_from_forgery :except => :create
  def index
    @concerts = Concert.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @concerts }
    end
  end

   def show
    @concert = Concert.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @concert }
    end
  end

  def new
    @concert = Concert.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @concert }
    end
  end

  def edit
    @concert = Concert.find(params[:id])
  end

  def create
    @concert = Concert.new(params[:concert])

    respond_to do |format|
      if @concert.save
        format.html { redirect_to @concert, notice: 'Concert was successfully created.' }
        format.json { render json: @concert, status: :created, location: @concert }
      else
        format.html { render action: "new" }
        format.json { render json: @concert.errors, status: :unprocessable_entity }
      end
    end
  end

  def update
    @concert = Concert.find(params[:id])

    respond_to do |format|
      if @concert.update_attributes(params[:concert])
        format.html { redirect_to @concert, notice: 'Concert was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @concert.errors, status: :unprocessable_entity }
      end
    end
  end

  def destroy
    @concert = Concert.find(params[:id])
    @concert.destroy

    respond_to do |format|
      format.html { redirect_to concerts_url }
      format.json { head :no_content }
    end
  end
end
