package com.infoshareacademy.pomaranczowi.financialanalyser.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "getPricesByDate",
                query = "FROM Price p " +
                        "WHERE p.id =:quotationId AND p.date=:localDate"),
        @NamedQuery(name = "getPricesFromDateToDate",
                query = "select p " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date>:startDate AND p.date<:endDate  order by p.date asc"), //order sortuje rosnaco aby miec dane po kolei przy upraszczaniu
        @NamedQuery(name = "getTheNextFreePriceId",
                query = "select MAX(p.id)" +
                        "from Price p"),
        @NamedQuery(name = "getMinDate",
                query = "select MIN(p.date)" +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode"),
        @NamedQuery(name = "getMaxDate",
                query = "select MAX(p.date)" +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode"),
        @NamedQuery(name = "getMaxOpenFromDateToDate",
                query = "select MAX(p.open) " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date>:startDate AND p.date<:endDate"),
        @NamedQuery(name = "getMinOpenFromDateToDate",
                query = "select MIN(p.open) " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date>:startDate AND p.date<:endDate"),
        @NamedQuery(name = "getMaxCloseFromDateToDate",
                query = "select MAX(p.close) " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date>:startDate AND p.date<:endDate"),
        @NamedQuery(name = "getMinCloseFromDateToDate",
                query = "select MIN(p.close) " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date>:startDate AND p.date<:endDate"),
        @NamedQuery(name = "getMaxHighFromDateToDate",
                query = "select MAX(p.high) " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date>:startDate AND p.date<:endDate"),
        @NamedQuery(name = "getMinHighFromDateToDate",
                query = "select MIN(p.high) " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date>:startDate AND p.date<:endDate"),
        @NamedQuery(name = "getMaxLowFromDateToDate",
                query = "select MAX(p.low) " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date>:startDate AND p.date<:endDate"),
        @NamedQuery(name = "getMinLowFromDateToDate",
                query = "select MIN(p.low) " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date>:startDate AND p.date<:endDate"),
        @NamedQuery(name = "getpricefromQurrencyCode",
                query = "select p " +
                        "from Price p " +
                        "join Quotation q on q.id = p.quotation.id " +
                        "where q.code=:quotationCode AND p.date=:userDate")
})
public class Price {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false, unique=true)
    private Long id;

    @Column
    private LocalDate date;
    @Column(precision = 11, scale = 4)
    private BigDecimal open;
    @Column(precision = 11, scale = 4)
    private BigDecimal high;
    @Column(precision = 11, scale = 4)
    private BigDecimal low;
    @Column(precision = 11, scale = 4)
    private BigDecimal close;
    @Column(precision = 11, scale = 4)
    private BigDecimal volume;
    private String quotationCode;
    @Column(insertable = false, updatable = false)
    private Long quotation_id;

    @ManyToOne
    @JoinColumn(name="quotation_id")
    private Quotation quotation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public String getQuotationCode() {
        return quotationCode;
    }

    public void setQuotationCode(String quotationCode) {
        this.quotationCode = quotationCode;
    }

    public Long getQuotation_id() {
        return quotation_id;
    }

    public void setQuotation_id(Long quotation_id) {
        this.quotation_id = quotation_id;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", quotationCode='" + quotationCode + '\'' +
                ", quotation=" + quotation +
                '}';
    }
}










