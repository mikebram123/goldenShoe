import { ProductSizeAssignment } from "./product-size-assignment";

export interface ProductBasketAssignment {
    productBasketAssignmentID:  number
    quantityOrdered: number
    linkedSizes: ProductSizeAssignment
}
