(ns musicbot.core
    (:gen-class)
    (:import 
        [net.dv8tion.jda.core JDABuilder AccountType JDA]
        [net.dv8tion.jda.core.hooks ListenerAdapter]))

(def prefix "RONNIEPICKERING")

(defn messageContent [event]
    (-> event .getMessage .getContent))

(defn compareMsgContent [event, check]
    (.equals (messageContent event) check))

(defn sendMessage [event, message] 
    (-> event .getTextChannel (.sendMessage message) .queue))

(defn handlePingCommand [event]
    (sendMessage event "Pong!"))

(defn handleHelpCommand [event]
    (sendMessage event "Get some help..."))

(defn handlePrefixMatch [event, message]
    (def args (.split (.substring message (.length prefix)) "\\s+"))
    (def name (nth args 0))
    (cond 
        (.equalsIgnoreCase name "ping") 
            (handlePingCommand event)
        (.equalsIgnoreCase name "help") 
            (handleHelpCommand event)
    ))

(def listener 
    (proxy [ListenerAdapter] []
        (onMessageReceived [event] 
            (def message (messageContent event))
            (if (.startsWith message prefix)
                (handlePrefixMatch event message)))
        (onStatusChange [event]
            (def status (-> event .getStatus .name))
            (prn (str "Status changed to " status)))))

(defn -main [& args]
    ; require token as argument
    (if (= (count args) 0)
        (throw (RuntimeException. "lol no token bro")))

    ; build jda, add listener & run async!
    (def jda (-> (JDABuilder. (AccountType/BOT)) 
        (.setToken (first args))
        (.addEventListener (into-array [listener]))
        (.buildAsync)))

    ; ya we made it
    (prn "JDA now running :)"))