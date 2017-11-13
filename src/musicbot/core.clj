(ns musicbot.core
    (:gen-class)
    (:import 
        [net.dv8tion.jda.core JDABuilder AccountType JDA]
        [net.dv8tion.jda.core.hooks ListenerAdapter]))

(defn compareMsgContent [event, check]
    (.equals (-> event .getMessage .getContent) check))

(defn sendMessage [event, message] 
    (-> event .getTextChannel (.sendMessage message) .queue))

(defn listener []
    (proxy [ListenerAdapter] []
        (onMessageReceived [event] 
            (if (compareMsgContent event "RONNIEPICKERING")
                (sendMessage event "DO YOU KNOW WHO I AM?")))))

(defn -main
    [& args]
    
    ; require token as argument
    (if (= (count args) 0)
        (throw (RuntimeException. "lol no token bro")))

    ; build jda, add listener & run async!
    (-> (JDABuilder. (AccountType/BOT)) 
        (.setToken (first args))
        (.addEventListener (into-array [(listener)]))
        (.buildAsync))

    ; ya we made it
    (prn "JDA now running :)"))