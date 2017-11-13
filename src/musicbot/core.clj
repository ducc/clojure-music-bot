(ns musicbot.core
    (:gen-class)
    (:import 
        [net.dv8tion.jda.core JDABuilder AccountType JDA]
        [net.dv8tion.jda.core.hooks ListenerAdapter]))

(defn listener []
    (proxy [ListenerAdapter] []
        (onMessageReceived [event] 
            (if (.equals (-> event .getMessage .getContent) "RONNIEPICKERING")
                (-> event .getTextChannel (.sendMessage "DO YOU KNOW WHO I AM?") .queue)))))

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