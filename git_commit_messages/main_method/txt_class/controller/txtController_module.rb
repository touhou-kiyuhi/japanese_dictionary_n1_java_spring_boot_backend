module TxtController
  def TxtController.txtReader path
    data = File.read path
    return data
  end

  def TxtController.txtWriter path, data
    File.open path, 'w' do |f|
      f.write data
    end
  end

  def TxtController.txtViewer data
    puts data
  end
end
