(ns modern-cljs.reagent
  (:require #?(:cljs [cljs.test :refer-macros [deftest are testing]])))

#?(:clj (deftest some-test
          (testing "some test"
            (are [expected actual] (= expected actual)
              "just trying out test framework."
              (first (:email ("me@googlenospam.com" "me@googlenospam.com")))))))


