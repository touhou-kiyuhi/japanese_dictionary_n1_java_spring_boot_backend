class Json_Messages_Generator
  attr_reader :messageType, :scope, :subject, :body, :footer, :data

  @@messageTypeList = ["feat", "fix", "refactor", "docs", "style", "test"]

  def initialize
    @messageType = ""
    @scope = ""
    @subject = ""
    @body = []
    @footer = []
    @data = {}
  end

  private

  def setMessageType
    puts format(
      "1: %s, 2: %s, 3: %s, 4: %s, 5: %s, 6: %s", 
      @@messageTypeList[0], 
      @@messageTypeList[1], 
      @@messageTypeList[2], 
      @@messageTypeList[3], 
      @@messageTypeList[4], 
      @@messageTypeList[5]
    )
    print "messageType: "
    messageTypeNum = gets.to_i
    @messageType = @@messageTypeList[messageTypeNum - 1]
  end

  def setScope
    print "scope: "
    @scope = gets.chomp
  end
  
  def setSubject
    print "subject: "
    @subject = gets.chomp
  end

  def setBody
    puts "輸入 EOF 停止"
    loop do
      print "body: "
      body = gets.chomp
      break if body == "EOF"
      @body << body
    end
  end

  def setFooter
    puts "輸入 EOF 停止"
    loop do
      print "footer: "
      footer = gets.chomp
      break if footer == "EOF"
      @footer << footer
    end
  end

  public

  def setData
    setMessageType
    setScope
    setSubject
    setBody
    setFooter
    @data = {
      header: {
        type: @messageType,
        scope: @scope,
        subject: @subject
      },
      body: @body,
      footer: @footer
    }
  end
end

def main
  jmg = Json_Messages_Generator.new
  jmg.setData()
  data = jmg.data
  print(data)
end

if __FILE__ == $0
  main
end