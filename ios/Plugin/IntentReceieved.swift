import Foundation

@objc public class IntentReceieved: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
