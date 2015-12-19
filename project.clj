(defproject advent-of-code "0.1.0-SNAPSHOT"
  :description "Solutions to adventofcode.com 2015"
  :url "https://github.com/spikymonkey/advent-of-code-2015"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [digest "1.4.4"]
                 [org.clojure/math.combinatorics "0.1.1"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot advent-of-code.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :test-selectors {:default (complement :slow)
                   :slow :slow})
