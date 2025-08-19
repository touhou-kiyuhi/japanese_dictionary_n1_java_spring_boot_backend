$LOAD_PATH << '.'
require "git_commit_messages/main_method/json_class/setting/jsonSettings_module.rb"
require "git_commit_messages/main_method/json_class/controller/jsonController_module.rb"
require "git_commit_messages/main_method/txt_class/setting/txtSettings_module.rb"
require "git_commit_messages/main_method/txt_class/controller/txtController_module.rb"

require "git_commit_messages/main_method/json_class/message_generator/jsonMessageGenerator.rb"

class Main
  include JsonSettings
  include TxtSettings

  attr_reader :jsonMessage, :txtMessage

  def initialize
    @jsonMessageGenerator = Json_Messages_Generator.new
    @jsonMessage = {}
    @txtMessage = ""
  end

  def setJsonMessage
    @jsonMessageGenerator.setData
    @jsonMessage = @jsonMessageGenerator.data
  end

  def setTxtMessage
    data = JsonController.jsonReader JSON_COMMIT_MESSAGE_PATH
    # header
    header = "%s%s%s\n\n" % [
      data["header"]["type"], 
      data["header"]["scope"] != ""? " (#{data["header"]["scope"]}): " : ": ", 
      data["header"]["subject"]
    ]
    # body 
    body = "%s" % data["body"].join("\n")
    # footer
    footer = data["footer"].empty?? "" : "\n\n%s" % data["footer"].join("\n")
    
    @txtMessage = header +body + footer
    puts @txtMessage
  end

  def saveJson data
    JsonController.jsonWriter JSON_COMMIT_MESSAGE_PATH, data
    data = JsonController.jsonReader JSON_COMMIT_MESSAGE_PATH
    JsonController.jsonViewer data
  end

  def saveTxt data
    TxtController.txtWriter TXT_COMMIT_MESSAGE_PATH, data
    data = TxtController.txtReader TXT_COMMIT_MESSAGE_PATH
    TxtController.txtViewer data
  end
end

def main
  m = Main.new
  # json 部分
  m.setJsonMessage
  data = m.jsonMessage
  m.saveJson data
  # txt 部分
  m.setTxtMessage
  data = m.txtMessage
  m.saveTxt data
end

if __FILE__ == $0
  main
end